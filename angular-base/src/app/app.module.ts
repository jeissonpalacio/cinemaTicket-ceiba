import { environment } from './../environments/environment';
import { MovieModule } from './feature/movie/movie.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { CoreModule } from '@core/core.module';
import { CookieService } from 'ngx-cookie-service';
import { AppComponent } from './app.component';
import { ActionReducer, StoreModule } from '@ngrx/store';

import { storeLogger } from 'ngrx-store-logger';
import { EffectsModule } from '@ngrx/effects';

export function logger(reducer: ActionReducer<object>): any {
  return storeLogger()(reducer);
}

export const metaReducers = environment.production ? [] : [logger];


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    StoreModule.forRoot({},{metaReducers}),
    EffectsModule.forRoot([]),
    MovieModule,
    CoreModule
  ],
  providers: [CookieService],
    bootstrap: [AppComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
