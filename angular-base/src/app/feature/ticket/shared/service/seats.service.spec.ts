import { SeatService } from './seats.service';
import { Seat } from './../model/seat';
import { MovieProjector } from '../model/projection';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { HttpService } from '@core/services/http.service';
import { environment } from 'src/environments/environment';


describe('ProjectorService', () => {
  let httpMock:HttpTestingController;
  let service: SeatService;
  const movieProjector:MovieProjector = new MovieProjector(1,new Date("2021-10-16"),"20:33:00",1,1);
  const apiEndpointSeatConsulta = `${environment.endpoint}/seats/${movieProjector.id}`;

  beforeEach(() => {
    const injector = TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[SeatService,HttpService]

    });
    httpMock = injector.inject(HttpTestingController);
    service = TestBed.inject(SeatService);
  });

  it('should be created', () => {
    const seatService:SeatService = TestBed.inject(SeatService);
    expect(seatService).toBeTruthy();
  });

  it('consultar movieprojector service',()=>{
      const dummySeat =[new Seat(1,'A1',1,1,1),new Seat(2,'A2',1,1,1)];

      service.consultar(movieProjector).subscribe(seat=>{
        expect(seat.length).toBe(2);
        expect(seat).toEqual(dummySeat);
      });
      const req = httpMock.expectOne(apiEndpointSeatConsulta);
      expect(req.request.method).toBe('GET');
      req.flush(dummySeat);
  });
});
