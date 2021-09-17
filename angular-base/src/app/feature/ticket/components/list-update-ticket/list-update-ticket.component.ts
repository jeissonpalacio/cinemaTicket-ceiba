import { HttpErrorResponse } from '@angular/common/http';
import { TicketService } from '../../shared/service/ticket.service';
import { Ticket } from '../../shared/model/ticket';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-update-ticket',
  templateUrl: './list-update-ticket.component.html',
  styleUrls: ['./list-update-ticket.component.css']
})
export class ListUpdateTicketComponent implements OnInit {

  ticketForm:FormGroup;
  tickets:Ticket[];
  alert: boolean=false;
  alerError: boolean=false;
  error;
  constructor(protected ticketService:TicketService, private router: Router) { }


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

  updateTicket(ticket:Ticket){
    this.ticketService.ticketSelect = ticket;
    this.router.navigateByUrl('/ticket/update');
  }

  claseAlertError(){
    this.alerError=false;
    this.error='';
  }
  closeAlert(){
    this.alert=false;
  }
}
