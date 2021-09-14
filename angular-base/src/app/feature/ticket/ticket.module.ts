import { MovieProjectorService } from './shared/service/movieprojector.service';
import { SeatService } from './shared/service/seats.service';

import { NgModule } from '@angular/core';

import { TicketRoutingModule } from './ticket-routing.module';
import { TicketComponent } from './components/ticket/ticket.component';
import { CreateTicketComponent } from './components/create-ticket/create-ticket.component';
import { SharedModule } from '@shared/shared.module';
import { TicketService } from './shared/service/ticket.service';
import { DeleteTicketComponent } from './components/delete-ticket/delete-ticket.component';
import { ListarActualizarTicketComponent } from './components/listar-actualizar-ticket/listar-actualizar-ticket.component';
import { ActualizarTicketComponent } from './components/actualizar-ticket/actualizar-ticket.component';


@NgModule({
  declarations: [
    TicketComponent,
    CreateTicketComponent,
    DeleteTicketComponent,
    ListarActualizarTicketComponent,
    ActualizarTicketComponent
  ],
  imports: [
    SharedModule,
    TicketRoutingModule
  ],
  providers:[
    MovieProjectorService,
    SeatService,
    TicketService
  ]
})
export class TicketModule { }
