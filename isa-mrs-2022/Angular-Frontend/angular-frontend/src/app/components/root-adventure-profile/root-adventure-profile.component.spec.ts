import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RootAdventureProfileComponent } from './root-adventure-profile.component';

describe('RootAdventureProfileComponent', () => {
  let component: RootAdventureProfileComponent;
  let fixture: ComponentFixture<RootAdventureProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RootAdventureProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RootAdventureProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
