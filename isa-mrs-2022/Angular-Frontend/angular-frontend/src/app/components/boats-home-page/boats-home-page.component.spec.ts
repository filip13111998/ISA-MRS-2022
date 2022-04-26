import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatsHomePageComponent } from './boats-home-page.component';

describe('BoatsHomePageComponent', () => {
  let component: BoatsHomePageComponent;
  let fixture: ComponentFixture<BoatsHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatsHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatsHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
