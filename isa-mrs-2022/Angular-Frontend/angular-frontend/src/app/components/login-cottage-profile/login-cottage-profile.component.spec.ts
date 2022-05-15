import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginCottageProfileComponent } from './login-cottage-profile.component';

describe('LoginCottageProfileComponent', () => {
  let component: LoginCottageProfileComponent;
  let fixture: ComponentFixture<LoginCottageProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginCottageProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginCottageProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
