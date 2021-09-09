import { Seat } from './../../shared/model/seat';
import { MovieProjector } from './../../shared/model/projection';
import { TicketService } from './../../shared/service/ticket.service';
import { SeatService } from './../../shared/service/seats.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MovieProjectorService } from '../../shared/service/movieprojector.service';
import { of } from 'rxjs';
import { ActivatedRoute} from '@angular/router';

import { CreateTicketComponent } from './create-ticket.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpService } from '@core/services/http.service';


describe('CreateTicketComponent test', () => {
  let component: CreateTicketComponent;
  let fixture: ComponentFixture<CreateTicketComponent>;
  let movieProjectorService: MovieProjectorService;
  let seatService:SeatService;
  let ticketService:TicketService; 
  let listMovieProjector: MovieProjector[] = [new MovieProjector(1,new Date("2021-10-16"),"20:33:00",1,1),new MovieProjector(2,new Date("2021-10-16"),"2021-10-19",1,1)]
  let seat:Seat[] = [new Seat(1,'A1',1,1,1),new Seat(2,'A2',1,1,1)];



  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateTicketComponent ],
      imports:[
        CommonModule,
        HttpClientModule,
        RouterTestingModule,
        ReactiveFormsModule,
        FormsModule
      ],
      providers:[
        HttpService,
        MovieProjectorService,
        SeatService,
        TicketService, 
        { provide: ActivatedRoute, useValue: {snapshot:{params:{movie:'{"id":1,"name":"Joker","typeMovie":"Comedy","lenght":"140","rating":4}'}}} }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTicketComponent);
    component = fixture.componentInstance;
    movieProjectorService = TestBed.inject(MovieProjectorService);
    seatService = TestBed.inject(SeatService);
    ticketService = TestBed.inject(TicketService);
    spyOn(movieProjectorService,'consultar').and.returnValue(
      of(listMovieProjector)
    );
    spyOn(seatService,'consultar').and.returnValue(of(seat));
    spyOn(ticketService,'crear').and.returnValue(
      of({'id':'1'})
    );

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('formulario es invalido cuando esta vacio', () => {
    expect(component.ticketForm.valid).toBeFalsy();
  });

  it('crear ticket', () => {
    expect(component.ticketForm.valid).toBeFalsy();
    component.ticketForm.controls.idClient.setValue(1);
    expect(component.ticketForm.valid).toBeTruthy();
    component.onSeats(listMovieProjector[0]);
    component.onSelectSeat(seat[0]);
    component.crear();

  });

});
