import { Movie } from '../../../movie/shared/model/movie';
import { MovieProjector } from '../model/projection';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { HttpService } from '@core/services/http.service';
import { environment } from 'src/environments/environment';

import { MovieProjectorService } from './movieprojector.service';

describe('ProjectorService', () => {
  let httpMock:HttpTestingController;
  let service: MovieProjectorService;
  const movie = new Movie(1,'Joker','Comedy','1:40',4); 
  const apiEndpointMovieConsulta = `${environment.endpoint}/movieprojector/${movie.id}`;

  beforeEach(() => {
    const injector = TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[MovieProjectorService,HttpService]

    });
    httpMock = injector.inject(HttpTestingController);
    service = TestBed.inject(MovieProjectorService);
  });

  it('should be created', () => {
    const movieProjectorService:MovieProjectorService = TestBed.inject(MovieProjectorService);
    expect(movieProjectorService).toBeTruthy();
  });

  it('consultar movieprojector service',()=>{
      const dummyMovieProjector =[new MovieProjector(1,new Date("2021-10-16"),"20:33:00",1,1),new MovieProjector(2,new Date("2021-10-16"),"2021-10-19",1,1)];
      service.consultar(movie).subscribe(movieProjector=>{
          expect(movieProjector.length).toBe(2);
          expect(movieProjector).toEqual(dummyMovieProjector);
      });

      const req = httpMock.expectOne(apiEndpointMovieConsulta);
      expect(req.request.method).toBe('GET');
      req.flush(dummyMovieProjector);
  });
});
