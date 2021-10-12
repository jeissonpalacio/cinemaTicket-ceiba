import { Router } from '@angular/router';
import { MovieService } from '../../shared/service/movie.service';
import { Movie } from '../../shared/model/movie';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-listar-movie',
  templateUrl: './list-movie.component.html',
  styleUrls: ['./list-movie.component.css']
})
export class ListMovieComponent implements OnInit {
  public listMovie: Observable<Movie[]>;

  //entities$ = this.store.pipe(select(selectAllMovies));

  constructor(protected movieService:MovieService,private router: Router) { }

  ngOnInit(): void {
  
    this.listMovie = this.movieService.consultMovie();

    ///this.store.dispatch(fromMovieActions.getMovieRequestStarted());
  }

  buyTicket(movie:Movie){
    this.router.navigate(['ticket/create',{movie: JSON.stringify(movie)}]);
  }

}
