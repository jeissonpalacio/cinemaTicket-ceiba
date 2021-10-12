

import { NgModule } from '@angular/core';

import { MovieComponent } from './components/movie/movie.component';
import { ListMovieComponent } from './components/listar-movie/list-movie.component';
import { MovieService } from './shared/service/movie.service';


@NgModule({
  declarations: [
    MovieComponent,
    ListMovieComponent
  ],
  providers:[
    MovieService
  ]
})
export class MovieModule { }
