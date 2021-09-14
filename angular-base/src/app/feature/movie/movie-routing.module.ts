import { MovieComponent } from './components/movie/movie.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarMovieComponent } from './components/listar-movie/listar-movie.component';

const routes: Routes = [
  {
    path:"",
    component: MovieComponent,
    children:[
      {
        path:'list',
        component: ListarMovieComponent
      }
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MovieRoutingModule { }
