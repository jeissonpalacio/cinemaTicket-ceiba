import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarActualizarTicketComponent } from './listar-actualizar-ticket.component';

describe('ListarActualizarTicketComponent', () => {
  let component: ListarActualizarTicketComponent;
  let fixture: ComponentFixture<ListarActualizarTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarActualizarTicketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarActualizarTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
