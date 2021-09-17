import { HttpErrorResponse } from '@angular/common/http';
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
  error;

  constructor(protected ticketService:TicketService) { }


  ngOnInit(): void {
    this.construirFormulario();
  }

  private construirFormulario(){
    this.ticketForm = new FormGroup({
      idClient: new FormControl('',[Validators.required])
    });
  }
  consultTickets(){
    this.ticketService.listarTickerPorIdClient(this.ticketForm.value.idClient).subscribe((data)=>{
      this.tickets = data;
    },(err:HttpErrorResponse) => {
      this.alerError = true;
      this.error = err.error.mensaje;
      throw new Error(err.error.mensaje);
    });

  }
  deleteTicket(ticket:Ticket){
    this.ticketService.deleteTicket(ticket.id).subscribe(()=>{
      this.ngOnInit();
      this.alert=true;
    },(err:HttpErrorResponse) => {
      this.alerError = true;
      console.log(err.status);
      console.log(err);
      console.log(err.error.mensaje);
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


}
