import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListUpdateTicketComponent } from './list-update-ticket.component';

describe('ListarActualizarTicketComponent', () => {
  let component: ListUpdateTicketComponent;
  let fixture: ComponentFixture<ListUpdateTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListUpdateTicketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListUpdateTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
