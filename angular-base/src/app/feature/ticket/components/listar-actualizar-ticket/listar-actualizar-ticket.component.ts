import { TicketService } from './../../shared/service/ticket.service';
import { Ticket } from './../../shared/model/ticket';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-actualizar-ticket',
  templateUrl: './listar-actualizar-ticket.component.html',
  styleUrls: ['./listar-actualizar-ticket.component.css']
})
export class ListarActualizarTicketComponent implements OnInit {

  ticketForm:FormGroup;
  tickets:Ticket[];
  alert: boolean=false;
  alerError: boolean=false;
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
    },(error) => {
      throw new Error(error);
    });

  }

  updateTicket(ticket:Ticket){
    this.ticketService.ticketSelect = ticket;
    this.router.navigateByUrl('/ticket/update');
  }

  claseAlertError(){
    this.alerError=false;
  }
  closeAlert(){
    this.alert=false;
  }
}
