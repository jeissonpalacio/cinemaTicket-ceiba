import { MovieProjector } from './../model/projection';
import { Injectable } from '@angular/core';
import { HttpService } from '@core-service/http.service';
import { environment } from 'src/environments/environment';
import { Seat } from '../model/seat';
@Injectable()
export class SeatService {

  constructor(protected http: HttpService) {}

  public consultar(movieProjector:MovieProjector) {
    return this.http.doGet<Seat[]>(`${environment.endpoint}/seats/${movieProjector.id}`, this.http.optsName('consultar movies'));
  }
}
