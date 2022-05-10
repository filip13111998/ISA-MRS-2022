import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterUserHistoryReservationComponent } from './register-user-history-reservation.component';

describe('RegisterUserHistoryReservationComponent', () => {
  let component: RegisterUserHistoryReservationComponent;
  let fixture: ComponentFixture<RegisterUserHistoryReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterUserHistoryReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterUserHistoryReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
