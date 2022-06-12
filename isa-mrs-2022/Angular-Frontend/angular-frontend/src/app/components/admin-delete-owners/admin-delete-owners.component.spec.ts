import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDeleteOwnersComponent } from './admin-delete-owners.component';

describe('AdminDeleteOwnersComponent', () => {
  let component: AdminDeleteOwnersComponent;
  let fixture: ComponentFixture<AdminDeleteOwnersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminDeleteOwnersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDeleteOwnersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
