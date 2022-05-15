import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginAdventureProfileComponent } from './login-adventure-profile.component';

describe('LoginAdventureProfileComponent', () => {
  let component: LoginAdventureProfileComponent;
  let fixture: ComponentFixture<LoginAdventureProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginAdventureProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginAdventureProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
