import { HttpErrorResponse } from '@angular/common/http';
import { SeatService } from '../../shared/service/seats.service';
import { Seat } from '../../shared/model/seat';
import { MovieProjector } from '../../shared/model/projection';
import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Ticket } from '../../shared/model/ticket';
import { TicketService } from '../../shared/service/ticket.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-actualizar-ticket',
  templateUrl: './update-ticket.component.html',
  styleUrls: ['./update-ticket.component.css']
})
export class UpdateTicketComponent implements OnInit {
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
                        },(err:HttpErrorResponse) => {
                          this.alerError = true;
                          this.error = err.error.mensaje;
                          throw new Error(err.error.mensaje);
                        });

      this.ticketService.listarSeatPorIdDelTicket(this.ticket.id)
                        .subscribe(x=>{
                          this.seats = x;
                          this.ticketForm.patchValue(x);
                        },(err:HttpErrorResponse) => {
                          this.alerError = true;
                          this.error = err.error.mensaje;
                          throw new Error(err.error.mensaje);
                        });
      

    }
  }

  loadSeat(){
    this.seatService.consultar(this.movieProjectorData).subscribe((data) => {
      this.ticketSeats = data;
    },(err:HttpErrorResponse) => {
      this.alerError = true;
      this.error = err.error.mensaje;
      throw new Error(err.error.mensaje);
    });
    this.edit = true;
  }

  cancel(){
    this.edit=false;
  }

  save(){
    let ticketnew = new Ticket(null,this.ticket.idClient,this.ticketForm.value.amount,this.ticket.idMovieProjector,[this.ticketForm.value.ticketSeats])
    console.log(ticketnew);
    console.log(this.ticket.id)
    this.ticketService.actualizar(this.ticket.id,ticketnew).subscribe(()=>{
        this.alert=true;
        this.edit=false;
        
    },(err:HttpErrorResponse) => {
      this.alerError = true;
      this.error = err.error.mensaje;
      throw new Error(err.error.mensaje);
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
