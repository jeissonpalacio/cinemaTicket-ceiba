import { NgModule } from '@angular/core';
import { EffectsModule } from '@ngrx/effects';
import { MovieEffects } from './store/effects/movie.effects';
import { StoreModule } from '@ngrx/store';
import { reducer } from './store/reductors/movie.reducer';  

@NgModule({
    imports:[
        StoreModule.forFeature('movie',reducer),
        EffectsModule.forFeature([MovieEffects])
    ]
})
export class MovieStoreModule{}