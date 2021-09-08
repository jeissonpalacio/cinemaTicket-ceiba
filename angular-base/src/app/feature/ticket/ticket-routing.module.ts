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
        path:'crear',
        component: CreateTicketComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketRoutingModule { }
