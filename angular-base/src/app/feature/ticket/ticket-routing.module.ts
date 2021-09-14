import { ActualizarTicketComponent } from './components/actualizar-ticket/actualizar-ticket.component';
import { ListarActualizarTicketComponent } from './components/listar-actualizar-ticket/listar-actualizar-ticket.component';
import { DeleteTicketComponent } from './components/delete-ticket/delete-ticket.component';
import { TicketComponent } from './components/ticket/ticket.component';
import { CreateTicketComponent } from './components/create-ticket/create-ticket.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path:"",
    component: TicketComponent,
    children:[
      {
        path:'create',
        component: CreateTicketComponent
      },
      {
        path:'edit',
        component:ListarActualizarTicketComponent
      },
      {
        path:'delete',
        component:DeleteTicketComponent
      },
      {
        path:'update',
        component:ActualizarTicketComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketRoutingModule { }
