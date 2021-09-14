import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarTicketComponent } from './actualizar-ticket.component';

describe('ActualizarTicketComponent', () => {
  let component: ActualizarTicketComponent;
  let fixture: ComponentFixture<ActualizarTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActualizarTicketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActualizarTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
