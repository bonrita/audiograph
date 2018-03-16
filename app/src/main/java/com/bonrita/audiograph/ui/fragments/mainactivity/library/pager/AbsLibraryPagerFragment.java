package com.bonrita.audiograph.ui.fragments.mainactivity.library.pager;


import com.bonrita.audiograph.ui.fragments.AbsMusicServiceFragment;
import com.bonrita.audiograph.ui.fragments.mainactivity.library.LibraryFragment;

public class AbsLibraryPagerFragment extends AbsMusicServiceFragment {

    public LibraryFragment getLibraryFragment() {
        return (LibraryFragment) getParentFragment();
    }

}
