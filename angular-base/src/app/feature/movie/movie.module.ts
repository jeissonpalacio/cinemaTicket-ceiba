import { MovieRoutingModule } from './movie-routing.module';
import { NgModule } from '@angular/core';

import { MovieComponent } from './components/movie/movie.component';
import { ListarMovieComponent } from './components/listar-movie/listar-movie.component';
import { SharedModule } from '@shared/shared.module';
import { MovieService } from './shared/service/movie.service';


@NgModule({
  declarations: [
    MovieComponent,
    ListarMovieComponent
  ],
  imports: [
    MovieRoutingModule,
    SharedModule
  ],
  providers:[
    MovieService
  ]
})
export class MovieModule { }
