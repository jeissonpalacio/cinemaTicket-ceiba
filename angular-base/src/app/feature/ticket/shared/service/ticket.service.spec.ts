import { TicketService } from './ticket.service';
import { Ticket } from './../model/ticket';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { HttpService } from '@core/services/http.service';
import { environment } from 'src/environments/environment';
import { HttpResponse } from '@angular/common/http';

describe('ProjectorService', () => {
  let httpMock:HttpTestingController;
  let service: TicketService;
  const apiEndpointMovieConsulta = `${environment.endpoint}/ticket/`;

  beforeEach(() => {
    const injector = TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[TicketService,HttpService]

    });
    httpMock = injector.inject(HttpTestingController);
    service = TestBed.inject(TicketService);
  });

  it('should be created', () => {
    const ticketService:TicketService = TestBed.inject(TicketService);
    expect(ticketService).toBeTruthy();
  });

  it('deberia crear un producto', () => {
    const ticket = new Ticket(1,15000.00,1,[1]);
    service.crear(ticket).subscribe((respuesta)=>{
        console.log(respuesta);
        expect(respuesta).toEqual(true);
    });
    const req = httpMock.expectOne(apiEndpointMovieConsulta);
    expect(req.request.method).toBe('POST');
    req.event(new HttpResponse<boolean>({body: true}));
  });
});
