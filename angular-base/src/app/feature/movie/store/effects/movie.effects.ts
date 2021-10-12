import { Movie } from './../../shared/model/movie';
import { fromMovieActions} from './../actions/movie.action';
import { MovieService } from './../../shared/service/movie.service';
import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import {tap, map, switchMap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';



@Injectable()
export class MovieEffects{

    constructor(private actions:Actions, private movieService:MovieService) {
        
    }
        loadMovies$ = createEffect(()=>
        this.actions.pipe(
            ofType(fromMovieActions.getMovieRequestStarted),
            tap(()=>console.log('ingreso')),
            switchMap(()=>            
                this.movieService.consultMovie().pipe(
                    map((movies:Movie[])=> fromMovieActions.getMovieRequestSucess(
                        {payload:movies
                        })
                    ),
                    catchError(error=> of(fromMovieActions.getMovieRequestFailed({payload:error})))
                )
            )
        )
    );
    

}