import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLoyalityPagComponent } from './admin-loyality-pag.component';

describe('AdminLoyalityPagComponent', () => {
  let component: AdminLoyalityPagComponent;
  let fixture: ComponentFixture<AdminLoyalityPagComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminLoyalityPagComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminLoyalityPagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
