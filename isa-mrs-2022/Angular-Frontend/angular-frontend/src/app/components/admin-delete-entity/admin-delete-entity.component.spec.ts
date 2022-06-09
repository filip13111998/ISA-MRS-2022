import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDeleteEntityComponent } from './admin-delete-entity.component';

describe('AdminDeleteEntityComponent', () => {
  let component: AdminDeleteEntityComponent;
  let fixture: ComponentFixture<AdminDeleteEntityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminDeleteEntityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDeleteEntityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
