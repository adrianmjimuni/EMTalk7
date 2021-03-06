package pad.ucm.fdi.emtalk.vista.llegadas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pad.ucm.fdi.emtalk.R;
import pad.ucm.fdi.emtalk.modelo.tiposApi.Arrive;

/**
 * Created by adrian on 13/07/16.
 */
public class AdaptadorLlegada extends RecyclerView.Adapter<AdaptadorLlegada.ViewHolder> {
    private static List<Arrive> info;
    private View.OnClickListener listener;
    public AdaptadorLlegada(View.OnClickListener listener, List<Arrive> i) {
        this.listener = listener;
        info = i;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_lista, parent, false);
        // set the view's size, margins, paddings and layout parameters
        v.setOnClickListener(listener);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText("Linea "+info.get(position).getLineId());
        holder.mTextView2.setText(fixTime(info.get(position).getBusTimeLeft()));
        holder.mTextView3.setText(info.get(position).getBusDistance() + " metros");
    }
    private String fixTime(Integer time) {
        if (time < 60) {
            return time.toString() + " segundos.";
        } else if (time == 999999) {
            return "Más de 20 minutos.";
        } else {
            Integer minutos = time / 60;
            Integer segundos = time % 60;
            return minutos.toString() + " minutos y " + segundos.toString() + " segundos.";
        }
    }
    @Override
    public int getItemCount() {
        return info.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public TextView mTextView2;
        public TextView mTextView3;
        public ViewHolder(TextView v, TextView v2) {
            super(v);
            mTextView = v;
            mTextView2 = v2;
        }

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) itemView.findViewById(R.id.autobus);
            mTextView2 = (TextView) itemView.findViewById(R.id.tiempo);
            mTextView3 = (TextView) itemView.findViewById(R.id.metros);

        }
    }


}