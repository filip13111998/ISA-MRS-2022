import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageAdminPageComponent } from './cottage-admin-page.component';

describe('CottageAdminPageComponent', () => {
  let component: CottageAdminPageComponent;
  let fixture: ComponentFixture<CottageAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageAdminPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
