package cu.xkoders.presentationcard.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.activities.DetailActivity;
import cu.xkoders.presentationcard.interfaces.OnActionPerformed;
import cu.xkoders.presentationcard.interfaces.OnItemClickListener;
import cu.xkoders.presentationcard.models.ContactoBK;
import cu.xkoders.presentationcard.utils.RecyclerItemClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Corporativos extends Fragment {

    private int mScrollOffset = 4;
    private ListView mListView;
    private FloatingActionButton mFabServices;
    private int mPreviousVisibleItem;
    private TextView nombreServicio;
    OnActionPerformed listener;

    public Corporativos() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_corporativos, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFabServices = (FloatingActionButton) view.findViewById(R.id.fab_services);

        final List items = new ArrayList();
        //esta info sale de la db
        items.add(new ContactoBK(R.drawable.ic_corporative_avatar, getResources().getString(R.string.name1)));
        items.add(new ContactoBK(R.drawable.ic_corporative_avatar, getResources().getString(R.string.name2)));
        items.add(new ContactoBK(R.drawable.ic_corporative_avatar, getResources().getString(R.string.name1)));
        items.add(new ContactoBK(R.drawable.ic_corporative_avatar, getResources().getString(R.string.name2)));
        items.add(new ContactoBK(R.drawable.ic_corporative_avatar, getResources().getString(R.string.name1)));
        items.add(new ContactoBK(R.drawable.ic_corporative_avatar, getResources().getString(R.string.name2)));




        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_services);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(new ServiceAdapter(items));


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > mScrollOffset) {
                    if (dy > 0) {
                        mFabServices.hide(true);
                    } else {
                        mFabServices.show(true);
                    }
                }
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Toast.makeText(getActivity(),"Test",Toast.LENGTH_LONG).show();
                        nombreServicio = (TextView) view.findViewById(R.id.nombre_servicio);

                       /* Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/html");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>"+nombreServicio.getText().toString()+"</p>"));
                        startActivity(Intent.createChooser(sharingIntent, "Comparte usando"));*/

                        Log.d("TEST",nombreServicio+""+position);

                        DetailActivity.createInstance(getActivity(), (ContactoBK) items.get(position));
                    }
                })
        );
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnActionPerformed)activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mFabServices.hide(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFabServices.show(true);
                mFabServices.setShowAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom));
                mFabServices.setHideAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom));
            }
        }, 300);

    }

    private class ServiceAdapter extends RecyclerView.Adapter<ViewHolder> {

        private Locale[] mLocales;
        private List<ContactoBK> items;
        private int lastPosition = -1;


        private ServiceAdapter(List<ContactoBK> items) {
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacto_bk_card_layout, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imagen.setImageResource(items.get(position).getImagen());
            holder.nombre.setText(items.get(position).getNombre());
            setSlideInLeft(holder.itemView, position);
        }

        private void setSlideInLeft(View view, int position) {
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
                view.startAnimation(animation);
                lastPosition = position;
            }
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            holder.clearAnimation();
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView imagen;
        public TextView nombre;
        public TextView precio;
        public View rootView;


        public ViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre_servicio);
            precio = (TextView) v.findViewById(R.id.precio);
            rootView = v;
        }

        public void bind(final ContactoBK item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("press","bindpressed");
                    listener.onItemClick(item);
                }
            });
        }

        public void clearAnimation() {
            rootView.clearAnimation();
        }
    }
}
