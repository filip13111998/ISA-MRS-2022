import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatAdminPageComponent } from './boat-admin-page.component';

describe('BoatAdminPageComponent', () => {
  let component: BoatAdminPageComponent;
  let fixture: ComponentFixture<BoatAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatAdminPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
