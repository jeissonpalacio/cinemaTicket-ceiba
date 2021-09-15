import { SeatService } from './../../shared/service/seats.service';
import { Seat } from './../../shared/model/seat';
import { MovieProjector } from './../../shared/model/projection';
import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Ticket } from './../../shared/model/ticket';
import { TicketService } from './../../shared/service/ticket.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-actualizar-ticket',
  templateUrl: './actualizar-ticket.component.html',
  styleUrls: ['./actualizar-ticket.component.css']
})
export class ActualizarTicketComponent implements OnInit {
  error;
  ticket: Ticket;
  alerError: boolean=false;
  alert: boolean=false;
  ticketForm: FormGroup;
  isAddMode!: boolean;
  submitted = false;
  movieProjectorData:MovieProjector;
  seats:Seat[];
  ticketSeats:Seat[];
  edit = false;
  constructor(protected ticketService:TicketService,protected seatService:SeatService,private formBuilder: FormBuilder) {}


  ngOnInit(): void {
    this.submitted = false;
    this.ticket = this.ticketService.ticketSelect;

    this.isAddMode = !this.ticket;

    this.ticketForm = this.formBuilder.group({
      id: ['',Validators.required],
      idClient:['',Validators.required],
      amount:['',Validators.required],
      movieProjector: [''],
      dateMovieProjector:[''],
      hourMovieProjector:[''],
      seats:[''],
      ticketSeats:['']
    });


    if(!this.isAddMode){
      this.ticketForm.patchValue({id:this.ticket.id})
      this.ticketForm.patchValue({idClient:this.ticket.idClient})
      this.ticketForm.patchValue({amount:this.ticket.amount})

      this.ticketService.listarMovieProjectionPorId(this.ticket.idMovieProjector)
                        .subscribe((data)=>{
                          this.movieProjectorData = data;
                          this.ticketForm.controls.movieProjector.patchValue(data.id);
                          this.ticketForm.controls.dateMovieProjector.patchValue(data.movieProjection);
                          this.ticketForm.controls.hourMovieProjector.patchValue(data.hourMovie);
                        },(error) => {
                          this.error = JSON.stringify(error.message);
                          this.alerError = true;
                          throw new Error(error);
                        });

      this.ticketService.listarSeatPorIdDelTicket(this.ticket.id)
                        .subscribe(x=>{
                          this.seats = x;
                          this.ticketForm.patchValue(x);
                        },(error) => {
                          this.error = JSON.stringify(error.message);
                          this.alerError = true;
                          throw new Error(error);
                        });
      

    }
  }

  loadSeat(){
    console.log('movieprojector' + this.movieProjectorData);
    this.seatService.consultar(this.movieProjectorData).subscribe((data) => {
      this.ticketSeats = data;
    },(error) => {
      this.alerError = true;
      this.error = JSON.stringify(error.message);
      throw new Error(error);
    });
    this.edit = true;
  }

  cancel(){
    console.log(this.ticketForm.value.ticketSeats);
    this.edit=false;
  }

  save(){
    let ticketnew = new Ticket(null,this.ticket.idClient,this.ticketForm.value.amount,this.ticket.idMovieProjector,[this.ticketForm.value.ticketSeats])
    this.ticketService.actualizar(this.ticket.id,ticketnew).subscribe((data)=>{
        console.log(data);
        this.alert=true;
        this.edit=false;
    },(error) => {
      this.alerError = true;
      this.error = JSON.stringify(error.message);
      throw new Error(error);
    });
  
  }
  
  claseAlertError(){
    this.alerError=false;
  }
  closeAlert(){
    this.alert=false;
  }


  get f() { return this.ticketForm.controls; }


}
