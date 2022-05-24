import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminNewPassComponent } from './admin-new-pass.component';

describe('AdminNewPassComponent', () => {
  let component: AdminNewPassComponent;
  let fixture: ComponentFixture<AdminNewPassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminNewPassComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminNewPassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
