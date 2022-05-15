import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RootCottageProfileComponent } from './root-cottage-profile.component';

describe('RootCottageProfileComponent', () => {
  let component: RootCottageProfileComponent;
  let fixture: ComponentFixture<RootCottageProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RootCottageProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RootCottageProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
