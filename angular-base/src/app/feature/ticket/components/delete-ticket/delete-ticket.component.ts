import { Ticket } from './../../shared/model/ticket';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../shared/service/ticket.service';

@Component({
  selector: 'app-delete-ticket',
  templateUrl: './delete-ticket.component.html',
  styleUrls: ['./delete-ticket.component.css']
})
export class DeleteTicketComponent implements OnInit {
  ticketForm:FormGroup;
  tickets:Ticket[];
  alert: boolean=false;
  alerError: boolean=false;
  constructor(protected ticketService:TicketService) { }


  ngOnInit(): void {
    this.construirFormulario();
  }

  private construirFormulario(){
    this.ticketForm = new FormGroup({
      idClient: new FormControl('',[Validators.required])
    });
  }
  consultarTickets(){
    this.ticketService.listarTickerPorIdClient(this.ticketForm.value.idClient).subscribe((data)=>{
      this.tickets = data;
    },(error)=>{
      console.log(error);
    });

  }
  eliminar(ticket:Ticket){
    console.log(ticket);
    this.ticketService.deleteTicket(ticket.id).subscribe((ticket)=>{
      console.log(ticket);
      this.ngOnInit();
      this.alert=true;
    },(error)=>{
      this.alerError=true;
      console.log(error);
    });
  }


  claseAlertError(){
    this.alerError=false;
  }
  closeAlert(){
    this.alert=false;
  }


}
