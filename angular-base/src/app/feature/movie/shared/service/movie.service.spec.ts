import { Movie } from './../model/movie';
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from 'src/environments/environment';

import { MovieService } from './movie.service';
import { HttpService } from '@core/services/http.service';



describe('MovieService', () => {
  let httpMock:HttpTestingController;
  let service: MovieService;
  const apiEndpointMovieConsulta = `${environment.endpoint}/movies`;

  beforeEach(() => {
    const injector = TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[MovieService,HttpService]
    });
    httpMock = injector.inject(HttpTestingController);
    service = TestBed.inject(MovieService);
  });

  it('should be created', () => {
    const movieService:MovieService = TestBed.inject(MovieService);
    expect(movieService).toBeTruthy();
  });


  it('listar movies',()=>{
    const dummyMovie = [new Movie(1,'Joker','Comedy','1:40',4)];
    service.consultMovie().subscribe(movie=>{
      expect(movie.length).toBe(1);
      expect(movie).toEqual(dummyMovie);
    });

    const req = httpMock.expectOne(apiEndpointMovieConsulta);
    expect(req.request.method).toBe('GET');
    req.flush(dummyMovie);


  });
});
