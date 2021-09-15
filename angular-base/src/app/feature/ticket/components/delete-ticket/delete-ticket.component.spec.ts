import { Ticket } from './../../shared/model/ticket';
import { TicketService } from './../../shared/service/ticket.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpService } from '@core/services/http.service';

import { DeleteTicketComponent } from './delete-ticket.component';
import { of } from 'rxjs';

describe('DeleteTicketComponent', () => {
  let component: DeleteTicketComponent;
  let fixture: ComponentFixture<DeleteTicketComponent>;
  let ticketService:TicketService;
  let ticketData: Ticket[] = [new Ticket(1,1,15000.00,1,[1,2])];
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[CommonModule,HttpClientModule,ReactiveFormsModule,FormsModule],
      declarations: [ DeleteTicketComponent ,HttpService,TicketService]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteTicketComponent);
    component = fixture.componentInstance;
    ticketService = TestBed.inject(TicketService);
    spyOn(ticketService,'listarTickerPorIdClient').and.returnValue(of(ticketData));
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('formulario es invalido cuando esta vacio', () => {
    expect(component.ticketForm.valid).toBeFalsy();
  });

  it('eliminar ticket',()=>{

     expect(component.ticketForm.valid).toBeFalsy();
     component.ticketForm.controls.idClient.setValue(1);
     expect(component.ticketForm.valid).toBeTruthy();
     component.consultTickets();
     component.deleteTicket(ticketData[0]);
     expect(ticketService.deleteTicket).toHaveBeenCalled();

  });

});
