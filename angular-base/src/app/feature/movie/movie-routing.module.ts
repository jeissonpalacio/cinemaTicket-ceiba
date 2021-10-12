import { MovieStoreModule } from './movie-store.module';
import { MovieComponent } from './components/movie/movie.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListMovieComponent } from './components/listar-movie/list-movie.component';

const routes: Routes = [
  {
    path:"",
    component: MovieComponent,
    children:[
      {
        path:'list',
        component: ListMovieComponent
      }
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes),
    MovieStoreModule
  ],
  exports: [RouterModule]
})
export class MovieRoutingModule { }
