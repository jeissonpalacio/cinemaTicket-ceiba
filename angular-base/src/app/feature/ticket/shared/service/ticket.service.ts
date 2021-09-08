import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { environment } from 'src/environments/environment';
import { Ticket } from '../model/ticket';
@Injectable()
export class TicketService {

  constructor(protected http: HttpService) {}

  public crear(ticket:Ticket) {
    return this.http.doPost<Ticket,any>(`${environment.endpoint}/ticket/`, ticket,this.http.optsName('crear ticket'));
}
}
