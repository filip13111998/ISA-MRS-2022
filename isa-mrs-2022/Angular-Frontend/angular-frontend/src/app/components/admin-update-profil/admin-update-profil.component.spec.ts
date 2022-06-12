import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUpdateProfilComponent } from './admin-update-profil.component';

describe('AdminUpdateProfilComponent', () => {
  let component: AdminUpdateProfilComponent;
  let fixture: ComponentFixture<AdminUpdateProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminUpdateProfilComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminUpdateProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
