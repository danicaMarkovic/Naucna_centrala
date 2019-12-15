import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepageGalleryComponent } from './homepage-gallery.component';

describe('HomepageGalleryComponent', () => {
  let component: HomepageGalleryComponent;
  let fixture: ComponentFixture<HomepageGalleryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomepageGalleryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
