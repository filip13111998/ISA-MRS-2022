import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RootBoatProfileComponent } from './root-boat-profile.component';

describe('RootBoatProfileComponent', () => {
  let component: RootBoatProfileComponent;
  let fixture: ComponentFixture<RootBoatProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RootBoatProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RootBoatProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
