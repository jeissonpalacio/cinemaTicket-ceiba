import { Movie } from './../../shared/model/movie';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { MovieService } from './../../shared/service/movie.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ListarMovieComponent } from './listar-movie.component';
import { HttpService } from '@core/services/http.service';

describe('ListarMovieComponent', () => {
  let component: ListarMovieComponent;
  let fixture: ComponentFixture<ListarMovieComponent>;
  let movieService:MovieService;
  let listMovie: Movie[] = [new Movie(1,'Joker','Comedy','1:40',4)]
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarMovieComponent ],
      imports:[
        CommonModule,
        HttpClientModule,
        RouterTestingModule],
      providers:[MovieService,HttpService]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarMovieComponent);
    component = fixture.componentInstance;
    movieService = TestBed.inject(MovieService);
    spyOn(movieService,'consultar').and.returnValue(
      of(listMovie)
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    component.listaMovie.subscribe(resultado=>{
      expect(1).toBe(resultado.length)
    })
  });
});
