import { UpdateTicketComponent } from './components/update-ticket/update-ticket.component';
import { ListUpdateTicketComponent } from './components/list-update-ticket/list-update-ticket.component';
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
        component:ListUpdateTicketComponent
      },
      {
        path:'delete',
        component:DeleteTicketComponent
      },
      {
        path:'update',
        component:UpdateTicketComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketRoutingModule { }
