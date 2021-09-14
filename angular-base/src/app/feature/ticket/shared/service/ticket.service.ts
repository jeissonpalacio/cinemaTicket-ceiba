import { Seat } from './../model/seat';
import { MovieProjector } from './../model/projection';
import { Ticket } from './../model/ticket';
import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { environment } from 'src/environments/environment';
@Injectable()
export class TicketService {


  ticketSeleccionado:Ticket;

  constructor(protected http: HttpService) {}

  public crear(ticket:Ticket) {
    return this.http.doPost<Ticket,any>(`${environment.endpoint}/ticket/`, ticket,this.http.optsName('crear ticket'));
  }
  public deleteTicket(id:number){
    return this.http.doDelete<any>(`${environment.endpoint}/ticket/${id}`,
    this.http.optsName('eliminar ticket'));
  }
  public actualizar(id:number,ticket:Ticket){
    return this.http.doPut<Ticket,any>(`${environment.endpoint}/ticket/${id}`,ticket,this.http.optsName("actualizar ticket"));
  }

  public listarTickerPorIdClient(id:number){
    return this.http.doGet<Ticket[]>(`${environment.endpoint}/ticket/consultar-por-id-cliente/${id}`, this.http.optsName('consultar tickets '));

  }

  public listarMovieProjectionPorId(id:number){
    return this.http.doGet<MovieProjector>(`${environment.endpoint}/movieprojector/movie-projector-id/${id}`, this.http.optsName('consultar tickets '));

  }

  public listarSeatPorIdDelTicket(id:number){
    return this.http.doGet<Seat[]>(`${environment.endpoint}/seats/consult-by-id-ticket/${id}`, this.http.optsName('consultar tickets '));
  }
}
