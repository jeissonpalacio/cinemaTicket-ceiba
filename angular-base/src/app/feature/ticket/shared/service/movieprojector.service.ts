import { MovieProjector } from '../model/projection';
import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { environment } from 'src/environments/environment';
import { Movie } from 'src/app/feature/movie/shared/model/movie';
@Injectable()
export class MovieProjectorService {

  movieSelect:Movie;

  constructor(protected http: HttpService) {}

  public consultar(movie:Movie) {
    return this.http.doGet<MovieProjector[]>(`${environment.endpoint}/movieprojector/${movie.id}`, this.http.optsName('consultar movies'));
  }
}
