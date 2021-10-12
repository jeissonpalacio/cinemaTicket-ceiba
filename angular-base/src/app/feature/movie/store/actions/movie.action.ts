import { Movie } from '../../shared/model/movie';
import { createAction, props } from '@ngrx/store';


export enum MovieActionTypes{
    GetMovieRequestStarted = '[Movie API] GET_MOVIE_REQUEST_STARTED',
    GetMovieRequestSuccess = '[Movie API ] GET_MOVIE_REQUEST_SUCESS',
    GetMovieRequestFailed  = '[Movie API] GET_MOVIE_REQUEST_FAILED'
}


export const getMovieRequestStarted = createAction(MovieActionTypes.GetMovieRequestStarted);

export const getMovieRequestSucess = createAction(
    MovieActionTypes.GetMovieRequestSuccess,
    props<{payload:Movie[]}>()
); 

export const getMovieRequestFailed = createAction(
    MovieActionTypes.GetMovieRequestFailed,
    props<{payload:Error| any}>()
)

export const fromMovieActions ={
    getMovieRequestStarted,
    getMovieRequestSucess,
    getMovieRequestFailed
}