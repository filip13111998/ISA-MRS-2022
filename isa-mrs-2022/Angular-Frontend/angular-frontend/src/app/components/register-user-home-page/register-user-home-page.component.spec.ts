import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterUserHomePageComponent } from './register-user-home-page.component';

describe('RegisterUserHomePageComponent', () => {
  let component: RegisterUserHomePageComponent;
  let fixture: ComponentFixture<RegisterUserHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterUserHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterUserHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
