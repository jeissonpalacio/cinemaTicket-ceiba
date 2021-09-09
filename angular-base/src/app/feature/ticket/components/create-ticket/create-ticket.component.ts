import { SeatService } from './../../shared/service/seats.service';
import { MovieProjector } from './../../shared/model/projection';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from './../../../movie/shared/model/movie';
import { Component, OnInit } from '@angular/core';
import { MovieProjectorService } from '../../shared/service/movieprojector.service';
import { Seat } from '../../shared/model/seat';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TicketService } from '../../shared/service/ticket.service';
import { Ticket } from '../../shared/model/ticket';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit {

  movie:Movie;
  movieProjector:MovieProjector[];
  currentProjection;
  seat:Seat[];
  seatTicket:Seat[] = new Array();
  ticketForm:FormGroup;
  ticket:Ticket;

  constructor(private router:Router,
    private activatedRoute:ActivatedRoute,
    protected movieProjectorService:MovieProjectorService,
    protected seatService:SeatService,
    protected ticketService:TicketService) { }

  ngOnInit(): void {
    if(Object.keys(this.activatedRoute.snapshot.params).length===0){
      this.router.navigate(['/movie/listar'],{skipLocationChange:true});
    }
    this.movie = JSON.parse(this.activatedRoute.snapshot.params.movie);
    this.movieProjectorService.consultar(this.movie).subscribe((movieProjectorData)=>{
        this.movieProjector = movieProjectorData;
      })
   
    this.construirFormulario();
  }

  onSeats(movieProjector:MovieProjector){
      this.currentProjection = movieProjector;
      this.seatService.consultar(this.currentProjection).subscribe((data)=>{
        this.seat= data;
      })

  }

  private construirFormulario(){
    this.ticketForm = new FormGroup({
      idClient: new FormControl('',[Validators.required])
    });
  }

  onSelectSeat(seatdata:Seat){
    let checkRoleExistence = ticketParam => this.seatTicket.some( (data:Seat) =>  data.id== ticketParam)
    if(!checkRoleExistence(seatdata.id)){
      this.seatTicket.push(seatdata);
    }else{
      this.seatTicket =this.seatTicket.filter((data)=>data.id!==seatdata.id);
    }
  }

  crear(){
    let amount = this.seatTicket.length*15000.00;
    let tickets = this.seatTicket.map(value =>{
      return value.id;
    })
    this.ticket = new Ticket(this.ticketForm.value.idClient,amount,this.currentProjection.id,tickets);
    this.ticketService.crear(this.ticket).subscribe((ticket)=>{
        console.log(ticket);
        this.seatTicket = [];
        this.ngOnInit();
    },(error)=>{
        console.log(error);
    });
  }

}
