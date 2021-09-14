import { Movie } from './../model/movie';
import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { environment } from 'src/environments/environment';
@Injectable()
export class MovieService {

  movieSelect:Movie;
  
  constructor(protected http: HttpService) {}

  public consultMovie() {
    return this.http.doGet<Movie[]>(`${environment.endpoint}/movies`, this.http.optsName('consultar movies'));
  }
}
