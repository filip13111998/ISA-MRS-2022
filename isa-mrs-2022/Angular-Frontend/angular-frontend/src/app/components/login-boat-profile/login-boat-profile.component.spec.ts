import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginBoatProfileComponent } from './login-boat-profile.component';

describe('LoginBoatProfileComponent', () => {
  let component: LoginBoatProfileComponent;
  let fixture: ComponentFixture<LoginBoatProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginBoatProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginBoatProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
