import { fromMovieActions } from './../actions/movie.action';
import { Movie } from './../../shared/model/movie';
import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';
import { Action, createReducer ,on} from '@ngrx/store';



export const MovieFeatureKey = 'Movie';


export interface State extends EntityState<Movie>{
    loaded:boolean;
    error?: Error | any
}


export const adapter: EntityAdapter<Movie> = createEntityAdapter<Movie>({
    selectId: movie => movie.id
})


export const initialState: State = adapter.getInitialState({
      // Additional entity state properties

      loaded:false,
      error:null
})



const _reducer = createReducer(
    initialState,
    on(fromMovieActions.getMovieRequestSucess, (state, { payload }) => {
        return adapter.addMany(payload, {
            ...state,
            loaded: true
          });
    }),
    on(fromMovieActions.getMovieRequestFailed,(state,{payload})=>{
        return {
            ...state,
            payload
        }
    })
  );
  

  export function reducer(state:State | undefined, action:Action){
      return _reducer(state,action);
  }