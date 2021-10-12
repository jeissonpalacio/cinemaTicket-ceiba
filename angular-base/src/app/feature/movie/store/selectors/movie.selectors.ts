import { ENTITY_FEATURE_KEY } from './../reductors/movie.reducer';
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State ,adapter} from '../reductors/movie.reducer';


const getMovieState = createFeatureSelector<State>(ENTITY_FEATURE_KEY);

const {selectIds,selectAll,selectTotal} = adapter.getSelectors();

export const selectMovieIds = createSelector(
    getMovieState,
    selectIds
)

export const selectAllMovies = createSelector(
    getMovieState,
    selectAll
)

export const selectMovieCount = createSelector(
    getMovieState,
    selectTotal
)

export const selectMovieLoaded = createSelector(
    getMovieState,
    state => state.loaded
)


export const selectMovieError = createSelector(
    getMovieState,
    state => state.error
)